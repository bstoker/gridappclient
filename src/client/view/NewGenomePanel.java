/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import client.control.Controller;
import client.model.ArchiveBuilder;
import client.model.Assembly;
import client.model.Genome;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author bram
 */
public class NewGenomePanel extends JPanel {

    private static final int REQUIRED_PROXY_TIME = 6;
    private static final int DEFAULT_PROXY_TIME = 12;
    private static final String DEFAULT_TEXT = "Sequence File";
    private static final Logger LOGGER = Logger.getLogger(NewGenomePanel.class.getSimpleName());
    private DefaultListModel<File> indexFiles;
    private File sequence;
    private Controller controller;

    /**
     * Creates new form GenomePanel
     */
    public NewGenomePanel(Controller controller) {
        this.controller = controller;
        initComponents();

        indexFiles = new DefaultListModel<>();
        indexFileList.setModel(indexFiles);
        fastaTextField.setText(DEFAULT_TEXT);

        for (Assembly assmebly : Assembly.values()) {
            genomeComboBox.addItem(assmebly);
        }

        uploadButton.setEnabled(false);
    }

    public Assembly getAssembly() {
        return (Assembly) genomeComboBox.getSelectedItem();
    }

    public List<File> getIndexFiles() {
        return Collections.list(indexFiles.elements());
    }

    public File getFastaFile() {
        return sequence;
    }

    private String parsePrefix(String name) {
        return name.replaceAll(".(rev.[12]|[1-4]).(bt2|ebwt)$", "");
    }

    public boolean isInputValid() {

        if (indexFiles.isEmpty()) {
            prefixField.setText("");
            prefixField.setEnabled(false);
            return false;
        }

        if (sequence == null || genomeComboBox.getSelectedItem() == null || indexFiles.size() != 6) {
            return false;
        }

        String indexName = indexFiles.get(0).getName();
        String extension = indexName.substring(indexName.lastIndexOf("."));
        String prefix = prefixField.getText();

        Map<String, Boolean> regexMap = new HashMap<>();
        regexMap.put(prefix + ".1" + extension + "$", false);
        regexMap.put(prefix + ".2" + extension + "$", false);
        regexMap.put(prefix + ".3" + extension + "$", false);
        regexMap.put(prefix + ".4" + extension + "$", false);
        regexMap.put(prefix + ".rev.1" + extension + "$", false);
        regexMap.put(prefix + ".rev.2" + extension + "$", false);

        for (File file : Collections.list(indexFiles.elements())) {
            for (String regex : regexMap.keySet()) {
                if (file.getName().matches(regex)) {
                    regexMap.put(regex, Boolean.TRUE);
                    continue;
                }
            }
        }

        for (boolean b : regexMap.values()) {
            if (!b) {
                return false;
            }
        }

        if (!sequence.getName().equals(prefix + ".fa")) {
            return false;
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        indexFileList = new javax.swing.JList();
        browseIndexButton = new javax.swing.JButton();
        removeIndexButton = new javax.swing.JButton();
        uploadButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        genomeComboBox = new javax.swing.JComboBox();
        fastaTextField = new javax.swing.JTextField();
        browseFastaFileButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        prefixField = new javax.swing.JTextField();

        indexFileList.setFocusable(false);
        indexFileList.setVisibleRowCount(6);
        jScrollPane1.setViewportView(indexFileList);

        browseIndexButton.setText("Browse");
        browseIndexButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseIndexButtonActionPerformed(evt);
            }
        });

        removeIndexButton.setText("Remove");
        removeIndexButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeIndexButtonActionPerformed(evt);
            }
        });

        uploadButton.setText("Upload");
        uploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Genome:");
        jLabel1.setFocusable(false);

        jLabel4.setText("Bowtie Files:");
        jLabel4.setFocusable(false);

        fastaTextField.setEditable(false);
        fastaTextField.setText("Sequence File");
        fastaTextField.setEnabled(false);

        browseFastaFileButton.setText("Browse");
        browseFastaFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseFastaFileButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Bowtie prefix: ");

        prefixField.setEditable(false);
        prefixField.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(genomeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(browseIndexButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeIndexButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fastaTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseFastaFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(uploadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prefixField)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(genomeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(removeIndexButton)
                    .addComponent(browseIndexButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(prefixField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fastaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseFastaFileButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(uploadButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void browseIndexButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseIndexButtonActionPerformed
        JFileChooser indexFileChooser = FileChooserFactory.getAddBowtieIndexFilesDialog();
        int value = indexFileChooser.showOpenDialog(this);

        if (value == JFileChooser.APPROVE_OPTION) {
            for (File file : indexFileChooser.getSelectedFiles()) {
                if (!indexFiles.contains(file)) {
                    indexFiles.addElement(file);
                }
            }
        }

        if (!indexFiles.isEmpty()) {
            String indexName = indexFiles.get(0).getName();
            String prefix = parsePrefix(indexName);
            prefixField.setText(prefix);
            prefixField.setEnabled(true);
        }

        uploadButton.setEnabled(isInputValid());
    }//GEN-LAST:event_browseIndexButtonActionPerformed

    private void removeIndexButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeIndexButtonActionPerformed
        for (Object file : indexFileList.getSelectedValuesList()) {
            indexFiles.removeElement(file);
        }
        uploadButton.setEnabled(isInputValid());
    }//GEN-LAST:event_removeIndexButtonActionPerformed

    private void browseFastaFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseFastaFileButtonActionPerformed
        JFileChooser fastaFileChooser = FileChooserFactory.getAddFastaFileDialog();
        int value = fastaFileChooser.showOpenDialog(this);
        if (value == JFileChooser.APPROVE_OPTION) {
            this.sequence = fastaFileChooser.getSelectedFile();
            fastaTextField.setText(fastaFileChooser.getSelectedFile().getAbsolutePath());
            fastaTextField.setEnabled(true);
        }
        uploadButton.setEnabled(isInputValid());
    }//GEN-LAST:event_browseFastaFileButtonActionPerformed

    private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadButtonActionPerformed

        Assembly assembly = (Assembly) genomeComboBox.getSelectedItem();
        Genome genome = new Genome(assembly, prefixField.getText());

        if (controller.getFiles().contains(genome)) {
            int value = JOptionPane.showConfirmDialog(this, "Do you want to overwrite it?", "Genome already exists", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (value != JOptionPane.YES_OPTION) {
                return;

            }

            boolean succes = false;
            try {
                succes = controller.removeFile(genome);
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            }

            if (!succes) {
                JOptionPane.showMessageDialog(this, "Unable to delete file " + genome.getID() + "!", "Error on delete", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (controller.getLocalProxyLifetime() < REQUIRED_PROXY_TIME) {
            PasswordDialog dialog = new PasswordDialog(this, "Local Proxy Required!");
            dialog.setVisible(true);

            if (dialog.getValue() != JOptionPane.OK_OPTION) {
                dialog.dispose();
                return;
            }

            boolean succes;
            try {
                succes = controller.createLocalProxy(dialog.getPassword(), DEFAULT_PROXY_TIME);
            } catch (IOException | InterruptedException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "I/O Error occured", JOptionPane.WARNING_MESSAGE);
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
                return;
            } finally {
                dialog.dispose();
            }

            if (!succes) {
                JOptionPane.showMessageDialog(this, "Invalid passphrase!", "Authentication Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        ArchiveBuilder archive = new ArchiveBuilder(Collections.list(indexFiles.elements()));
        archive.putFile(sequence);
        controller.addFile(genome, archive);
        getTopLevelAncestor().setVisible(false);
    }//GEN-LAST:event_uploadButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseFastaFileButton;
    private javax.swing.JButton browseIndexButton;
    private javax.swing.JTextField fastaTextField;
    private javax.swing.JComboBox genomeComboBox;
    private javax.swing.JList indexFileList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField prefixField;
    private javax.swing.JButton removeIndexButton;
    private javax.swing.JButton uploadButton;
    // End of variables declaration//GEN-END:variables
}
